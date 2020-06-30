package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.service.BillService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    public List<BillProjection> getBillByNumber(String line) throws ValidationException {
        if (!StringUtils.isBlank(line)) {
            return billService.getBillByNumber(line);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }
    }

    public List<BillProjection> getBillAll()  {
        return billService.getBillAll();
    }

    public List<BillProjection> getBillDate(String dni, String first, String second) throws ValidationException {
        if (!StringUtils.isBlank(dni) && !StringUtils.isBlank(first) && !StringUtils.isBlank(second)){
            return billService.getListBillByDate(dni, first, second);
        } else {
            throw new ValidationException("Wrong parameters (empty, null, or wrong)");
        }

    }
}
