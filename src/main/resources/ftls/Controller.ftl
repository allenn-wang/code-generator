package ${rootPackageName}.${controllerPackageName};

import ${rootPackageName}.${servicePackageName}.${table.className}${serviceClassName};
import ${rootPackageName}.${basePackageName}.${controllerPackageName}.${basePackageName?cap_first}${controllerClassName};
import ${rootPackageName}.${entityPackageName}.${table.className}Example;
import ${rootPackageName}.${entityPackageName}.${table.className};
import ${rootPackageName}.${dtoPackageName}.${table.className}${dtoClassName};
import ${rootPackageName}.${basePackageName}.common.PageObject;
import ${rootPackageName}.${basePackageName}.common.ResultObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author：  ${author}
* @date：    ${date}
* @description：  ${table.annotation}
*/
@Controller
@RequestMapping("/${table.className?uncap_first}")
public class ${table.className}${controllerClassName} extends ${basePackageName?cap_first}${controllerClassName} {
	private static final Logger logger = Logger.getLogger(${table.className}${controllerClassName}.class);

	@Autowired
	private ${table.className}${serviceClassName} ${table.className?uncap_first}${serviceClassName};

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultObject add(@RequestBody ${table.className}${dtoClassName} ${table.className?uncap_first}${dtoClassName}) {
        int count = ${table.className?uncap_first}${serviceClassName}.insert(${table.className?uncap_first}${dtoClassName});
        if (count > 0) {
            return ResultObject.success(count);
        } else {
            return ResultObject.failed();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultObject update(@RequestBody ${table.className}${dtoClassName} ${table.className?uncap_first}${dtoClassName}) {
        int count = ${table.className?uncap_first}${serviceClassName}.updateByPrimaryKeySelective(${table.className?uncap_first}${dtoClassName});
        if (count > 0) {
            return ResultObject.success(count);
        } else {
            return ResultObject.failed();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultObject delete(@RequestBody String userId) {
        int count = ${table.className?uncap_first}${serviceClassName}.deleteByPrimaryKey(userId);
        if (count > 0) {
            return ResultObject.success(count);
        } else {
            return ResultObject.failed();
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ResultObject get(@RequestBody String userId) {
        ${table.className}${dtoClassName} ${table.className?uncap_first}${dtoClassName} = ${table.className?uncap_first}${serviceClassName}.selectDtoByPrimaryKey(userId);
        return ResultObject.success(${table.className?uncap_first}${dtoClassName});
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResultObject list(@RequestParam(value = "pageSize") Integer pageSize,
                             @RequestParam(value = "pageNum") Integer pageNum) {
        ${table.className}Example ${table.className?uncap_first}Example = new ${table.className}Example();
        return ResultObject.success(
                    PageObject.restPage(${table.className?uncap_first}${serviceClassName}.queryList${dtoClassName}(${table.className?uncap_first}Example, pageNum, pageSize)));
    }

}