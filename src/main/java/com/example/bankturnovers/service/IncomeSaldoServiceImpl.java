//package com.example.bankturnovers.service;
//
//import com.example.bankturnovers.entity.IncomeSaldo;
//import com.example.bankturnovers.repository.IncomeSaldoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class IncomeSaldoServiceImpl implements IncomeSaldoService{
//
//    @Autowired
//    public IncomeSaldoRepository incomeSaldoRepository;
//
//    @Override
//    public void create(IncomeSaldo incomeSaldo) { incomeSaldoRepository.save(incomeSaldo); }
//
//    @Override
//    public List<IncomeSaldo> readAll() { return incomeSaldoRepository.findAll(); }
//
//    @Override
//    public IncomeSaldo read(int id) { return incomeSaldoRepository.findById(id).orElse(new IncomeSaldo()); }
//
//    @Override
//    public boolean update(IncomeSaldo incomeSaldo, int id) {
//        if(incomeSaldoRepository.existsById(id)) {
//            incomeSaldo.setId(id);
//            incomeSaldoRepository.save(incomeSaldo);
//            return true;
//        }
//        else return false;
//    }
//
//    @Override
//    public boolean delete(int id) {
//        if(incomeSaldoRepository.existsById(id)) {
//            incomeSaldoRepository.deleteById(id);
//            return true;
//        }
//        else return false;
//    }
//}
