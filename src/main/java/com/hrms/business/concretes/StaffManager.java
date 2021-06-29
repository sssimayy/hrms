package com.hrms.business.concretes;

import com.hrms.business.abstracts.CandidateService;
import com.hrms.business.abstracts.StaffService;
import com.hrms.core.utilities.results.*;
import com.hrms.dataAccess.abstracts.StaffDao;
import com.hrms.entities.concretes.Staff;
import com.hrms.entities.concretes.dtos.StaffUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class StaffManager implements StaffService {

    private StaffDao staffDao;
    private CandidateService candidateService;

    @Autowired
    public StaffManager(StaffDao staffDao, CandidateService candidateService) {
        this.staffDao = staffDao;
        this.candidateService = candidateService;
    }

    @Override
    public Result create(Staff staff) {
        if(staff.getPassword().length() <=6){
            return new ErrorResult("Şifre 6 karakterden uzun olmalıdır");
        } if (!isValid(staff.getEmail())) {
            return new ErrorResult("Email is not valid format");

        }else if(candidateService.getByEmail(staff.getEmail()).getData() != null){
            return new ErrorResult("Bu email zaten kayıtlı");
        }
        staff.setMailVerify(true);
        staffDao.save(staff);
        return new SuccessResult("Kayıt yapıldı");
    }

    @Override
    public DataResult<List<Staff>> getAll() {
        return new SuccessDataResult<List<Staff>>(this.staffDao.findAll(),"Data listelendi");
    }

    @Override
    public Result update(StaffUpdateDto staffUpdateDto) {
        if(!this.staffDao.existsById(staffUpdateDto.getStaffId())){
            return new ErrorResult("Böyle bir personel yok");
        }else if(staffUpdateDto.getFirstName().length()<2){
            return new ErrorResult("İsim 2 karakterden kısa olamaz");
        }else if(staffUpdateDto.getLastName().length()<2){
            return new ErrorResult("Soy isim 2 karakterden kısa olamaz");
        }else if (!isValid(staffUpdateDto.getEmail())) {
            return new ErrorResult("Geçerli bir email değil");
        }

        Staff staff=this.staffDao.getById(staffUpdateDto.getStaffId());
        staff.setFirstName(staffUpdateDto.getFirstName());
        staff.setLastName(staffUpdateDto.getLastName());
        staff.setEmail(staffUpdateDto.getEmail());
        this.staffDao.save(staff);
        return new SuccessResult("Bilgiler kaydedildi");
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
