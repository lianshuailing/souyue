package com.souyue.qa.client.impl;

import com.souyue.common.pojo.Result;
import com.souyue.common.pojo.StatusCode;
import com.souyue.qa.client.BaseClient;
import org.springframework.stereotype.Component;

@Component
public class BaseClientImpl implements BaseClient {

    // 举例：如日志操作、友好提示、、、、、、
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR, "熔断器触发了！");
    }
}
