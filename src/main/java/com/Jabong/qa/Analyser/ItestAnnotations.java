package com.Jabong.qa.Analyser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class ItestAnnotations implements IAnnotationTransformer{

	public void transform(ITestAnnotation Annotation, Class TestClass, Constructor TestConstructor, Method TestMethod)
	{
		Annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
