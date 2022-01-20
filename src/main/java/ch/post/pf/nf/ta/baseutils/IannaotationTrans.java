package ch.post.pf.nf.ta.baseutils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class IannaotationTrans implements IAnnotationTransformer{

	@Override
	public void transform(ITestAnnotation arg0, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		arg0.setRetryAnalyzer(RetryAnalyser.class);
	}

}
