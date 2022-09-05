//package com.example.bankturnovers.service;
//
//import com.example.bankturnovers.entity.IncomeSaldo;
//import com.example.bankturnovers.entity.Turnovers;
//import com.example.bankturnovers.repository.IncomeSaldoRepository;
//import com.example.bankturnovers.repository.TurnoversRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class TurnoversServiceImpl implements TurnoversService{
//    @Autowired
//    TurnoversRepository turnoversRepository;
//
//    @Override
//    public void create(Turnovers turnovers) { turnoversRepository.save(turnovers); }
//
//    @Override
//    public List<Turnovers> readAll() { return turnoversRepository.findAll(); }
//
//    @Override
//    public Turnovers read(int id) { return turnoversRepository.findById(id).orElse(new Turnovers()); }
//
//    @Override
//    public boolean update(Turnovers turnovers, int id) {
//        if(turnoversRepository.existsById(id)) {
//            turnovers.setId(id);
//            turnoversRepository.save(turnovers);
//            return true;
//        }
//        else return false;
//    }
//
//    @Override
//    public boolean delete(int id) {
//        if(turnoversRepository.existsById(id)) {
//            turnoversRepository.deleteById(id);
//            return true;
//        }
//        else return false;
//    }
//}
