
SCRIPT.include("mytest", "test1");
SCRIPT.include(null, "Test1", "Test1")
SCRIPT.include(null, "Test1", "test2")

return "Test, this is main script!" + mytest.go() + 
	oscript_Test1_Test1.run() + oscript_Test1_test2.run() + 
	oscript_Test1_test2.go()