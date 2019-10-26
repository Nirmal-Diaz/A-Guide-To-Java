package basic.package1;

public class PublicClass {
    //This scope has access to "PublicClass < package1 < basic < world" by scoping theory

    public static String publicMember = "world > basic > package1 > PublicClass > publicMember"; 
    protected static String protectedMember = "world > basic > package1 > PublicClass > protectedMember";
    static String packagePrivateMember = "world > basic > package1 > PublicClass > packagePrivateMember";
    private static String privateMember = "world > basic > package1 > PublicClass > privateMember";

    public void testAccess() {
        //This scope has access to "testAccess < PublicClass < package1 < basic < world" by scoping theory
        
        System.out.println(basic.package1.PublicClass.publicMember);
        System.out.println(basic.package1.PublicClass.protectedMember);
        System.out.println(basic.package1.PublicClass.packagePrivateMember);
        System.out.println(basic.package1.PublicClass.privateMember);

        System.out.println(basic.package1.PackagePrivateClass.publicMember);
        System.out.println(basic.package1.PackagePrivateClass.protectedMember);
        System.out.println(basic.package1.PackagePrivateClass.packagePrivateMember);
        System.out.println(basic.package1.PackagePrivateClass.privateMember); //Do not have access

        System.out.println(basic.package2.PublicClass.publicMember); //Do not have access
        System.out.println(basic.package2.PublicClass.protectedMember); //Do not have access
        System.out.println(basic.package2.PublicClass.packagePrivateMember); //Do not have access
        System.out.println(basic.package2.PublicClass.privateMember); //Do not have access
        //We can access "basic.package2.PublicClass.protectedMember" through a subclass of "basic.package2.PublicClass"
        class Subclass extends basic.package2.PublicClass {
            public void testAccess() {
                System.out.println(basic.package2.PublicClass.publicMember);
                System.out.println(basic.package2.PublicClass.protectedMember);
                System.out.println(basic.package2.PublicClass.packagePrivateMember); //Do not have access
                System.out.println(basic.package2.PublicClass.privateMember); //Do not have access
            }
        }

        //This scope doesn't have access to "basic.package2.PackagePrivateClass" at all
        System.out.println(basic.package2.PackagePrivateClass.publicMember); //Do not have access
        System.out.println(basic.package2.PackagePrivateClass.protectedMember); //Do not have access
        System.out.println(basic.package2.PackagePrivateClass.packagePrivateMember); //Do not have access
        System.out.println(basic.package2.PackagePrivateClass.privateMember); //Do not have access

        
    }
}

class PackagePrivateClass {
    //This scope has access to "PackagePrivateClass < package1 < basic < world" by scoping theory

    public static String publicMember = "world > basic > package1 > PackagePrivateClass > publicMember"; 
    protected static String protectedMember = "world > basic > package1 > PackagePrivateClass > protectedMember";
    static String packagePrivateMember = "world > basic > package1 > PackagePrivateClass > packagePrivateMember";
    private static String privateMember = "world > basic > package1 > PackagePrivateClass > privateMember";

    public void testAccess() {
        //This scope has access to "testAccess < PackagePrivateClass < package1 < basic < world" by scoping theory

        System.out.println(basic.package1.PublicClass.publicMember);
        System.out.println(basic.package1.PublicClass.protectedMember);
        System.out.println(basic.package1.PublicClass.packagePrivateMember);
        System.out.println(basic.package1.PublicClass.privateMember); //Do not have access

        System.out.println(basic.package1.PackagePrivateClass.publicMember);
        System.out.println(basic.package1.PackagePrivateClass.protectedMember);
        System.out.println(basic.package1.PackagePrivateClass.packagePrivateMember);
        System.out.println(basic.package1.PackagePrivateClass.privateMember); //Do not have access

        System.out.println(basic.package2.PublicClass.publicMember);
        System.out.println(basic.package2.PublicClass.protectedMember); //Do not have access
        System.out.println(basic.package2.PublicClass.packagePrivateMember); //Do not have access
        System.out.println(basic.package2.PublicClass.privateMember); //Do not have access
        //We can access "basic.package2.PublicClass.protectedMember" through a subclass of "basic.package2.PublicClass"
        class Subclass extends basic.package2.PublicClass {
            public void testAccess() {
                System.out.println(basic.package2.PublicClass.publicMember);
                System.out.println(basic.package2.PublicClass.protectedMember);
                System.out.println(basic.package2.PublicClass.packagePrivateMember); //Do not have access
                System.out.println(basic.package2.PublicClass.privateMember); //Do not have access
            }
        }

        //This scope doesn't have access to "basic.package2.PackagePrivateClass" at all
        System.out.println(basic.package2.PackagePrivateClass.publicMember); //Do not have access
        System.out.println(basic.package2.PackagePrivateClass.protectedMember); //Do not have access
        System.out.println(basic.package2.PackagePrivateClass.packagePrivateMember); //Do not have access
        System.out.println(basic.package2.PackagePrivateClass.privateMember); //Do not have access
    }
}