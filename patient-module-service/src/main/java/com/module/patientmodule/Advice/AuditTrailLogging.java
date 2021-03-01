package com.module.patientmodule.Advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AuditTrailLogging is a annotation for audit trail.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditTrailLogging {

}
