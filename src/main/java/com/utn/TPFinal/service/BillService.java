package com.utn.TPFinal.service;

import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<BillProjection> getBillByNumber(String line) throws SQLException {
        return billRepository.getBillByNumber(line);
    }

    public List<BillProjection> getBillAll() throws SQLException{
        return billRepository.getBillAll();
    }

    public List<BillProjection> getListBillByDate(String dni, String firstDate, String secondDate) throws SQLException{
        return billRepository.getListBillByDate(dni,firstDate, secondDate);
    }


}
