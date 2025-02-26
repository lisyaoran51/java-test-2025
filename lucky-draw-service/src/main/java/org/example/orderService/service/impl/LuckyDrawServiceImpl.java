package org.example.orderService.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.example.orderService.dao.PrizeDao;
import org.example.orderService.domain.po.Prize;
import org.example.orderService.domain.vo.order.*;
import org.example.orderService.service.LuckyDrawService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class LuckyDrawServiceImpl extends ServiceImplBase<PrizeDao, Prize> implements LuckyDrawService {

    @Autowired
    PrizeDao prizeDao;

    @Override
    public List<Prize> multiDraw(MultiDrawVo multiDrawVo) {
        log.info("draw: {}", multiDrawVo.getCount());
        LambdaQueryWrapper<Prize> queryWrapper = Wrappers.lambdaQuery(Prize.class);
        var prizes = list(queryWrapper);
        var current = 0.0d;
        var prizeRange = new HashMap<Map.Entry<Double, Double>, Prize>();

        for (var p : prizes) {
            prizeRange.put(new AbstractMap.SimpleEntry(current, current+p.getProbability()), p);
            current += p.getProbability();
        }
        if (current < 1) {
            prizeRange.put(new AbstractMap.SimpleEntry(current, 1d), null);
        }

        var result = new ArrayList<Prize>();
        for (var i = 0; i < multiDrawVo.getCount(); i++ ) {
            Prize prize = null;
            var random = Math.random();
            for(var entry : prizeRange.entrySet()) {
                if (entry.getKey().getKey() <= random && entry.getKey().getValue() > random) {
                    prize = entry.getValue();
                }
            }

            if (prize == null) {
                result.add(null);
                continue;
            }

            if (prize.getQuantity() == 0) {
                result.add(null);
                continue;
            }

            var success = draw(prize);
            if (!success) {
                result.add(null);
                continue;
            }

            result.add(prize);
        }

        return result;
    }

    public boolean draw(Prize prize) {
        LambdaUpdateWrapper<Prize> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.gt(Prize::getQuantity, 0);
        updateWrapper.eq(Prize::getId,prize.getId());
        updateWrapper.setSql("quantity = quantity - 1");
        return update(updateWrapper);
    }

    @Override
    public Prize oneDraw() {
        return null;
    }
}

