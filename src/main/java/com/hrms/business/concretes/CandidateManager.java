package com.hrms.business.concretes;

import com.hrms.business.abstracts.CandidateService;
import com.hrms.core.adapter.abstracts.mernis.MernisService;
import com.hrms.core.utilities.check.CheckResult;
import com.hrms.core.utilities.check.ConfirmPasswordCheck;
import com.hrms.core.utilities.check.EmailCheck;
import com.hrms.core.utilities.check.NationalIdentityCheck;
import com.hrms.core.utilities.results.*;
import com.hrms.dataAccess.abstracts.CandidateDao;
import com.hrms.entities.concretes.Candidate;
import com.hrms.entities.concretes.dtos.CandidateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CandidateManager implements CandidateService {

    private CandidateDao candidateDao;
    private MernisService mernisService;
    private EmailCheck emailCheck;
    private NationalIdentityCheck nationalIdentityCheck;
    private ConfirmPasswordCheck confirmPasswordCheck;

    @Autowired
    public CandidateManager(CandidateDao candidateDao, MernisService mernisService, EmailCheck emailCheck, NationalIdentityCheck nationalIdentityCheck, ConfirmPasswordCheck confirmPasswordCheck) {
        this.candidateDao = candidateDao;
        this.mernisService = mernisService;
        this.emailCheck = emailCheck;
        this.nationalIdentityCheck = nationalIdentityCheck;
        this.confirmPasswordCheck = confirmPasswordCheck;
    }


    @Override
    public Result add(CandidateDto candidateDto) {
        Result result = CheckResult.checkResult(Arrays.asList(emailCheck.check(candidateDto),
                nationalIdentityCheck.check(candidateDto),
                confirmPasswordCheck.check(candidateDto),
                mernisService.checkForPersonelInformation(candidateDto)));
//        boolean identityCheck = candidateDao.existsByNationalIdentity(candidateDto.getNationalIdentity());
//
//        boolean emailCheck = candidateDao.existsByEmail(candidateDto.getEmail());
//
//        if (emailCheck == true) {
//            return new ErrorResult("Email already exist");
//        }
//
//        if (identityCheck == true) {
//            return new ErrorResult("Identity already exist");
//        }
//        if (!candidateDto.getPassword().equals(candidateDto.getPasswordCheck())) {
//            return new ErrorResult("Passwords are not equal!");
//        }

        if (!isValid(candidateDto.getEmail())) {
            return new ErrorResult("Email is not valid format");
        }
        if (result.isSuccess()) {

            return new SuccessResult("Success");
        }

        Candidate candidateRegister = new Candidate();
        candidateRegister.setName(candidateDto.getName());
        candidateRegister.setSurname(candidateDto.getSurname());
        candidateRegister.setEmail(candidateDto.getEmail());
        candidateRegister.setVerificationCode(candidateDto.getVerificationCode());
        candidateRegister.setNationalIdentity(candidateDto.getNationalIdentity());
        candidateRegister.setBirthDate(candidateDto.getBirthDate());
        candidateRegister.setPassword(candidateDto.getPassword());
        candidateRegister.setPasswordCheck(candidateDto.getPasswordCheck());
        candidateDao.save(candidateRegister);

        return new SuccessResult("Registration successful!");
    }

    @Override
    public DataResult<List<Candidate>> getAll() {
        return new SuccessDataResult<>(candidateDao.findAll(), "Data listed successfully");
    }

    @Override
    public DataResult<Candidate> getByCandidateId(int id) {
        return new SuccessDataResult<Candidate>(this.candidateDao.getById(id), "Data listed successfully");
    }

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
