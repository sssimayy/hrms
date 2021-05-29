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

        if (result.isSuccess()) {
            String emailVerificationCode = RandomString.make(64);
            candidate.setVerificationCode(emailVerificationCode);

            candidateDao.saveAndFlush(candidate);

            String emailMessage = emailVerificationService.sendEmailVerificationCode();
            return new SuccessDataResult<Candidate>(candidate, emailMessage);
        }
        return new ErrorDataResult<>(candidate, result.getMessage());
    }

    @Override
    public DataResult<List<Candidate>> getAll() {
        return new SuccessDataResult<>(candidateDao.findAll(), "Data listed successfully");
    }
}
