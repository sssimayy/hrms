package com.hrms.core.utilities.check;

import com.hrms.core.utilities.results.Result;
import com.hrms.core.utilities.results.SuccessResult;

import java.util.List;

public class CheckResult {

    public static Result checkResult(List<Result> results) {
        for (Result result : results) {
            if (!result.isSuccess()) {
                return result;
            }
        }
        return new SuccessResult();
    }

}
