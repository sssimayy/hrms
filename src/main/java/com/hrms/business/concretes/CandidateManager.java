package com.hrms.business.concretes;

import com.hrms.business.abstracts.CandidateService;
import com.hrms.core.adapter.abstracts.email.EmailVerificationService;
import com.hrms.core.adapter.abstracts.mernis.MernisService;
import com.hrms.core.utilities.check.CheckResult;
import com.hrms.core.utilities.check.ConfirmPasswordCheck;
import com.hrms.core.utilities.check.EmailCheck;
import com.hrms.core.utilities.check.NationalIdentityCheck;
import com.hrms.core.utilities.results.DataResult;
import com.hrms.core.utilities.results.ErrorDataResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.core.utilities.results.SuccessDataResult;
import com.hrms.dataAccess.abstracts.CandidateDao;
import com.hrms.entities.concretes.Candidate;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CandidateManager implements CandidateService {

    private CandidateDao candidateDao;
    private EmailCheck emailCheck;
    private NationalIdentityCheck nationalIdentityCheck;
    private ConfirmPasswordCheck confirmPasswordCheck;
    private MernisService mernisService;
    private EmailVerificationService emailVerificationService;

    @Autowired
    public CandidateManager(CandidateDao candidateDao, EmailCheck emailCheck, NationalIdentityCheck nationalIdentityCheck,
                            ConfirmPasswordCheck confirmPasswordCheck, MernisService mernisService, EmailVerificationService emailVerificationService) {
        this.candidateDao = candidateDao;
        this.emailCheck = emailCheck;
        this.nationalIdentityCheck = nationalIdentityCheck;
        this.confirmPasswordCheck = confirmPasswordCheck;
        this.mernisService = mernisService;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public DataResult<Candidate> add(Candidate candidate) {

        Result result = CheckResult.checkResult(Arrays.asList(emailCheck.check(candidate),
                nationalIdentityCheck.check(candidate),
                confirmPasswordCheck.check(candidate),
                mernisService.checkForPersonelInformation(candidate)));

        if (!isValid(candidate.getEmail())) {
            return new ErrorDataResult<>(candidate, "Email is not valid format");
        }

        if (result.isSuccess()) {
            String emailVerificationCode = RandomString.make(64);
            candidate.setVerificationCode(emailVerificationCode);

            candidateDao.saveAndFlush(candidate);

            String emailMessage = emailVerificationService.sendEmailVerificationCode();
            return new SuccessDataResult<>(candidate, emailMessage);
        }
        return new ErrorDataResult<>(candidate, result.getMessage());
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
