package com.hrms.business.concretes;

import com.hrms.business.abstracts.EmployerService;
import com.hrms.core.utilities.results.*;
import com.hrms.dataAccess.abstracts.EmployerDao;
import com.hrms.dataAccess.abstracts.EmployerUpdateDao;
import com.hrms.dataAccess.abstracts.StaffDao;
import com.hrms.entities.concretes.Employer;
import com.hrms.entities.concretes.EmployerUpdate;
import com.hrms.entities.concretes.dtos.EmployerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmployerManager implements EmployerService {

    private EmployerDao employerDao;
    private StaffDao staffDao;
    private EmployerUpdateDao employerUpdateDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao, StaffDao staffDao, EmployerUpdateDao employerUpdateDao) {
        this.employerDao = employerDao;
        this.staffDao = staffDao;
        this.employerUpdateDao = employerUpdateDao;
    }

    @Override
    public Result add(EmployerDto employer) {
        if (!employer.getPassword().equals(employer.getPasswordRepeat())) {
            return new ErrorResult("Passwords do not match!");
        }

        Employer emailInDb = employerDao.findByEmail(employer.getEmail());

        if (emailInDb != null) {
            return new ErrorResult("This mail address already exist!");
        }

        String email = employer.getEmail();
        String website = employer.getWebsite();

        if (!email.substring(email.indexOf("@") + 1).equals(website.substring(website.indexOf(".") + 1))) {
            return new ErrorResult("Email address should be same with domain name!");
        }

        Employer employerRegister = new Employer();
        employerRegister.setCompanyName(employer.getCompanyName());
        employerRegister.setWebsite(employer.getWebsite());
        employerRegister.setEmail(employer.getEmail());
        employerRegister.setPhone(employer.getPhone());
        employerRegister.setPassword(employer.getPassword());


        employerDao.save(employerRegister);

        return new SuccessResult("Successful");
    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<>(employerDao.findAll(), "Data is listed");

    }

    @Override
    public DataResult<Employer> findByEmail(String email) {
        Employer employerInDB = employerDao.findByEmail(email);

        if (employerInDB == null) {
            return new ErrorDataResult<>("Data not found!");
        }
        return new SuccessDataResult<>(employerInDB, "Data is found");
    }

    @Override
    public Result verifyUpdate(int employerUpdateId, int staffId) {
        if(!this.employerUpdateDao.existsById(employerUpdateId)){
            return new ErrorResult("Böyle bir güncelleme talebi yok");
        }else if(!this.staffDao.existsById(staffId)){
            return new ErrorResult("There is no such a staff");
        }
        EmployerUpdate employerUpdate=this.employerUpdateDao.getById(employerUpdateId);
        Employer employer=this.employerDao.getById(employerUpdate.getEmployerId());

        employerUpdate.setVerifyed(true);
        employerUpdate.setStaffId(staffId);
        employerUpdate.setVerifyDate(LocalDate.now());
        this.employerUpdateDao.save(employerUpdate);

        employer.setEmail(employerUpdate.getEmail());
        employer.setCompanyName(employerUpdate.getCompanyName());
        employer.setPhone(employerUpdate.getPhoneNumber());
        employer.setWebsite(employerUpdate.getWebSite());
        employer.setWaitingUpdate(false);
        this.employerDao.save(employer);
        return new SuccessResult("Information is updated");
    }

    @Override
    public Result update(EmployerUpdate employerUpdate) {
        employerUpdate.setId(0);
        employerUpdate.setCreateDay(LocalDate.now());

        if(employerUpdate.getCompanyName().length()<2){
            return new ErrorResult("Şirket adı 2 karakterden kısa olamaz");
        }else if(employerUpdate.getPhoneNumber().length()!=11){
            return new ErrorResult("Telefon numarası 11 haneli olmalıdır");
        } if (!isValid(employerUpdate.getEmail())) {
            return new ErrorResult("Email is not valid format");
        }
        else if(this.employerDao.existsById(employerUpdate.getEmployerId())){
            return new ErrorResult(("Böyle bir işveren yok"));
        }
        Employer employer=this.employerDao.getById(employerUpdate.getEmployerId());
        this.employerUpdateDao.save(employerUpdate);
        employer.setWaitingUpdate(true);
        this.employerDao.save(employer);
        return new SuccessResult("Güncelleme isteği gönderildi personelin onayı ardından görünür olacaktır");
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
