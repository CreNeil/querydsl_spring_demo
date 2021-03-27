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
import org.springframework.web.bind.annotation.RequestParam;
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
        computer.setMouse(mouse);
        computerRepository.save(computer);
        return "hello";
    }

    @GetMapping("/monitor")
    public String getHello2(@RequestParam Long id) {
        Long time1 = System.currentTimeMillis();
        Monitor monitor = new Monitor();
        monitor.setSeries("SUMSANG" + "_" + time1.toString());
        monitor.setComputer(computerRepository.getOne(id));
        Monitor monitor2 = new Monitor();
        monitor2.setSeries("SUMSANG-2" + "_" + time1.toString());
        monitor2.setComputer(computerRepository.getOne(id));
        Monitor monitor3 = new Monitor();
        monitor3.setSeries("SUMSANG-3" + "_" + time1.toString());
        monitor3.setComputer(computerRepository.getOne(id));
        monitorRepository.save(monitor);
        monitorRepository.save(monitor2);
        monitorRepository.save(monitor3);
        return "hello";
    }
}
