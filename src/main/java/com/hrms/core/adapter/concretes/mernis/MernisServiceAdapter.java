package com.hrms.core.adapter.concretes.mernis;

import com.hrms.core.adapter.abstracts.mernis.MernisService;
import com.hrms.core.utilities.results.ErrorResult;
import com.hrms.core.utilities.results.Result;
import com.hrms.core.utilities.results.SuccessResult;
import com.hrms.entities.concretes.Candidate;

import com.hrms.mernis.MHTKPSPublicSoap;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class MernisServiceAdapter implements MernisService {

    MHTKPSPublicSoap cfdkpsPublicSoap = new MHTKPSPublicSoap();

    @Override
    public Result checkForPersonelInformation(Candidate candidate) {

        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(candidate.getBirthDate());
            int year = calendar.get(Calendar.YEAR);
            boolean verifyNationalIdentity =cfdkpsPublicSoap.TCKimlikNoDogrula(Long.parseLong(candidate.getNationalIdentity()),
                    candidate.getName(), candidate.getSurname(), year);
            if (verifyNationalIdentity) {
                return new SuccessResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ErrorResult("The personal informations you entered are incorrect.");
    }
}
