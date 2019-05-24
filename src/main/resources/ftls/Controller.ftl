package ${rootPackageName}.${controllerPackageName};

import ${rootPackageName}.${servicePackageName}.${table.className}${serviceClassName};
import ${rootPackageName}.${basePackageName}.${controllerPackageName}.${basePackageName?cap_first}${controllerClassName};

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
* @author：  ${author}
* @date：    ${date}
* @description：  ${table.annotation}
*/
@Controller
public class ${table.className}${controllerClassName} extends ${basePackageName?cap_first}${controllerClassName} {
	private static final Logger logger = Logger.getLogger(${table.className}${controllerClassName}.class);

	@Autowired
	private ${table.className}${serviceClassName} ${table.className?uncap_first}${serviceClassName};

}