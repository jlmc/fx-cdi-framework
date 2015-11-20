package org.xine.fx.cdi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.interceptor.AroundConstruct;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.xine.fx.inject.core.FXMLController;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

@FXMLController
@Interceptor
public class FXMLControllerInterceptor {

	@AroundConstruct
	public void createCdiFXMLController(final InvocationContext invocationContext) throws Exception {
		// Performs c-tor invocation. Afterwards "invocationContext.getTarget()" will no longer return "null".
		invocationContext.proceed();

		// Fetches the newly created annotated object and it's class.
		final Object target = invocationContext.getTarget();
		final Class<?> targetClass = target.getClass();

		final FXMLController annotation = targetClass.getAnnotation(FXMLController.class);
		if (annotation == null) {
			throw new IllegalStateException(String.format("No @FXMLController annotation could be retrieved from class %s.", targetClass.getName()));
		}

		final FXMLLoader fxmlLoader = new CdiFXMLLoader();

		CdiFXMLLoaderFactory.initializeFXMLLoader(
				fxmlLoader, 
				targetClass, 
				annotation.location(), 
				annotation.resources(), 
				annotation.charset());
		fxmlLoader.setRoot(null);
		fxmlLoader.setController(target);

		//        CdiFXMLLoaderFactory.initializeFXMLLoader(
		//                fxmlLoader,
		//                targetClass,
		//                annotation.location(),
		//                annotation.resources(),
		//                annotation.charset());
		//        fxmlLoader.setRoot(target);
		//        fxmlLoader.setController(target);

		// We now have to perform the actual loading of the FXML document.
		// ... we have to make sure that this happens on the right (thus: FX application) thread, though!
		if (Platform.isFxApplicationThread()) {
			fxmlLoader.load();
		} else {
			final CountDownLatch latch = new CountDownLatch(1);
			Platform.runLater(() -> {
				try {
					//final Object loaded = 
					fxmlLoader.load();
					latch.countDown();
				} catch (final IOException e) {
					throw new IllegalStateException("Loading of FXML file failed.", e);
				}
			});
			latch.await();
		}
	}

}
