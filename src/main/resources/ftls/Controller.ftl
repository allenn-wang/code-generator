package ${basePackageName}.${controllerPackageName};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ${basePackageName}.${serivcePackageName}.${table.className}${serviceClassName};
import ${basePackageName}.${controllerPackageName}.base;

/**
* @author：${author} <br/>
* @date：${date} <br/>
* @description：${table.annotation}
*/
@Controller
public class ${table.className}${controllerClassName} extends Base${controllerClassName} {
	private static final Logger logger = LoggerFactory.getLogger(${table.className}${controllerClassName}.class);

	@Autowired
	private ${table.className}${serviceClassName} ${table.className?uncap_first}${serviceClassName};

}