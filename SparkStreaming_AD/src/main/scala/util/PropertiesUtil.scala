package util

import java.io.InputStreamReader
import java.util.Properties

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: SparkStreaming_AD
 * Package: util
 * ClassName: PropertiesUtil 
 *
 * @author 18729 created on date: 2020/11/2 10:24
 * @version 1.0
 * @since JDK 1.8
 */
object PropertiesUtil {

  def load(propertiesName: String): Properties = {

    val prop = new Properties()
    prop.load(new InputStreamReader(Thread.currentThread().getContextClassLoader.getResourceAsStream(propertiesName), "UTF-8"))
    prop
  }

}
