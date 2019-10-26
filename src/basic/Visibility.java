package basic;

//NOTE: In Java, "visibility" is managed by keywords known as "Access Level Modifiers"
//NOTE: Top level: public > package-private
//NOTE: Member level: public > protected> package-private > private
//==================================================================
//|  Modifier         |  Class  |  Package  |  Subclass  |  World  |
//==================================================================
//|  pubic            |  Y      |  Y        |  Y         |  Y      |
//|  protected        |  Y      |  Y        |  Y         |  N      |
//|  package-private  |  Y      |  Y        |  N         |  N      |
//|  private          |  Y      |  N        |  N         |  N      |
//==================================================================

//Top Level Access Level Modifiers
//NOTE: "public": Accessible from "any scope"
//NOTE: "package-private": Accessible only from "owner package scope"

//Member Level Access Level Modifiers
//NOTE: "public": Accessible from "any scope"
//NOTE: "protected": Accessible only from "owner package scope" and "subclass scope"
//NOTE: "package-private": Accessible only from "owner package scope"
//NOTE: "private": Accessible only from "owner class scope"

//Access Level can be easily recognized by additionally knowing the following "scoping" rules
//1. Any scope have access to itself
//2. Any scope have access to its ancestral scopes
//3. No scope can have access to its descendant scopes unless allowed by access level modifiers

//Use the following hierarchy built in this tutorial to learn about Java's "visibility"
//                                                 world(src)
//                                                     |
//                                                   basic
//                                                     |
//                       =============================================================
//                       |                                                           |
//                    package1                                                    package2
//                       |                                                           |
//          ==============================                            ==============================
//          |                            |                            |                            |
//    PublicClass               PackagePrivateClass             PublicClass               PackagePrivateClass
//    -publicMember             -publicMember                   -publicMember             -publicMember
//    -protectedMember          -protectedMember                -protectedMember          -protectedMember 
//    -packagePrivateMember     -packagePrivateMember           -packagePrivateMember     -packagePrivateMember
//    -privateMember            -privateMember                  -privateMember            -privateMember