package org.modelversioning.emfprofile.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.modelversioning.emfprofileapplication.validation.InapplicableExtensionApplicationConstraintValidatorTest;
import org.modelversioning.emfprofileapplication.validation.LowerBoundConstraintValidatorTest;
import org.modelversioning.emfprofileapplication.validation.UpperBoundConstraintValidatorTest;

@RunWith(Suite.class)
@SuiteClasses({ BasicProfileFacadeTest.class, MetaProfileFacadeTest.class,
		LowerBoundConstraintValidatorTest.class,
		UpperBoundConstraintValidatorTest.class,
		InapplicableExtensionApplicationConstraintValidatorTest.class,
		SubsetProfileFacadeTest.class, RedefineProfileFacadeTest.class })
public class AllEMFProfileTests {

}
