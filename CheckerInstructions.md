### Configuring Nullness Checker on IntelliJ
 (as per https://checkerframework.org/manual/#intellij)
 
-> Clone this repository.
 
-> Set project SDK to 1.8
 
-> Change the language level to 8
 
-> Add the annotated JDK library to the bootclasspath. On the Java compiler settings page in the “Additional command line parameters” section, add:
    -Xbootclasspath/p:".../checker-framework/checker/dist/jdk8.jar"
   OR 
Search for "org.checkerframework" under `File | Project Structure | Modules | Dependencies` and add the jar.
  
-> Configure an annotation profile under `File | Settings | Build, Execution and Deployment | Annotation Processors`
 
   1. Create an annotation profile.

   2. Associate the AFRS module with that profile.

   3. Add .../checker-framework/checker/dist/checker.jar to the processor path.

   4. Add "org.checkerframework.checker.nullness.NullnessChecker" in the “Processor FQ Name” section.

   5. Add .../checker-framework/checker/dist/checker-qual.jar, as a dependency to the AFRS module)
    
