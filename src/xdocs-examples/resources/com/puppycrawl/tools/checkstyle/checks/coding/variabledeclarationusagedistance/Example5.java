/*xml
<module name="Checker">
  <module name="TreeWalker">
    <module name="VariableDeclarationUsageDistance">
      <property name="ignoreFinal" value="false"/>
    </module>
  </module>
</module>
*/
package com.puppycrawl.tools.checkstyle.checks.coding.variabledeclarationusagedistance;

// xdoc section -- start
public class Example5 {

  public void foo1() {
    int num;        // violation, distance = 4
    final double PI;   // violation, distance = 5
    System.out.println("Statement 1");
    System.out.println("Statement 2");
    System.out.println("Statement 3");
    num = 1;
    PI = 3.14;
  }

  public void foo2() {
    int a;          // OK, used in different scope
    int b;          // OK, used in different scope
    int count = 0;  // OK, used in different scope

    {
      System.out.println("Inside inner scope");
      a = 1;
      b = 2;
      count++;
    }
  }
}
// xdoc section -- end
