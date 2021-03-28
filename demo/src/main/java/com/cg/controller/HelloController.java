package com.cg.controller;

import com.cg.domain.Computer;
import com.cg.domain.KeyBoard;
import com.cg.domain.Monitor;
import com.cg.domain.Mouse;
import com.cg.repository.ComputerRepository;
import com.cg.repository.MonitorRepository;
import com.cg.repository.MouseRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/generate")
public class HelloController {

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private MouseRepository mouseRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @GetMapping("/base")
    public String getHello() {
        Computer computer = new Computer();
        KeyBoard keyBoard = new KeyBoard();
        keyBoard.setMakers("IKBC");
        keyBoard.setOwner("Neil");
        keyBoard.setSeries("Normal");
        computer.setKeyBoard(keyBoard);
        Long time1 = System.currentTimeMillis();
        Mouse mouse = new Mouse();
        mouse.setSeries("SteelSeries" + "_" + time1.toString());
        mouse.setMakers("SteelSeries");
        mouse.setOwner("Neil");
        mouseRepository.save(mouse);
        Monitor monitor = new Monitor();
        monitor.setSeries("SAMSUNG" + "_" + time1.toString());
        monitor.setMakers("SAMSUNG");
        monitor.setOwner("Neil Young");
        monitorRepository.save(monitor);
        computer.setMouse(mouse);
        computer.setMonitor(monitor);
        computerRepository.save(computer);
        return "hello";
    }
}
