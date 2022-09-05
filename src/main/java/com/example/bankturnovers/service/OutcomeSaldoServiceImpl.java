//package com.example.bankturnovers.service;
//
//import com.example.bankturnovers.entity.IncomeSaldo;
//import com.example.bankturnovers.entity.OutcomeSaldo;
//import com.example.bankturnovers.repository.IncomeSaldoRepository;
//import com.example.bankturnovers.repository.OutcomeSaldoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class OutcomeSaldoServiceImpl implements OutcomeSaldoService{
//
//    @Autowired
//    public OutcomeSaldoRepository outcomeSaldoRepository;
//
//    @Override
//    public void create(OutcomeSaldo outcomeSaldo) { outcomeSaldoRepository.save(outcomeSaldo); }
//
//    @Override
//    public List<OutcomeSaldo> readAll() { return outcomeSaldoRepository.findAll(); }
//
//    @Override
//    public OutcomeSaldo read(int id) { return outcomeSaldoRepository.findById(id).orElse(new OutcomeSaldo()); }
//
//    @Override
//    public boolean update(OutcomeSaldo outcomeSaldo, int id) {
//        if(outcomeSaldoRepository.existsById(id)) {
//            outcomeSaldo.setId(id);
//            outcomeSaldoRepository.save(outcomeSaldo);
//            return true;
//        }
//        else return false;
//    }
//
//    @Override
//    public boolean delete(int id) {
//        if(outcomeSaldoRepository.existsById(id)) {
//            outcomeSaldoRepository.deleteById(id);
//            return true;
//        }
//        else return false;
//    }
//}
