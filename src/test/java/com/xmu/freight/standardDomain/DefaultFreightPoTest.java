package com.xmu.freight.standardDomain;

import com.xmu.freight.util.JacksonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hsx
 * @version 1.0
 * @date 2019/12/14 23:13
 */
class DefaultFreightPoTest {

    @Test
    void validate() {
        String jsonString = "{\"dest\":[]}";
        if(jsonString == null)
        {
            System.out.println(2);
        }
          jsonString = org.apache.commons.text.StringEscapeUtils.unescapeJson(jsonString);
        List<Integer> regionIds = JacksonUtil.parseIntegerList(jsonString, "dest");
        if(regionIds==null)
        {
            System.out.println("1");
        }
        if(regionIds.toString().equals("[]"))
        {
            System.out.println(3);
        }
    }
}