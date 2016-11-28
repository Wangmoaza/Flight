# Enter "sh readme.txt" at the Linux command prompt to execute
# the following test cases in batch. Read .out files for the detailed
# results from executions.

java MainAir Hawaiian-airports.txt Hawaiian-flights.txt Hawaiian-customers10.txt > my01.out
java MainAir Hawaiian-airports.txt Hawaiian-flights.txt Hawaiian-customers100.txt > my02.out
java MainAir Hawaiian-airports.txt Hawaiian-flights.txt Hawaiian-customers-bogus.txt > my03.out
java MainAir Hawaiian-airports.txt Hawaiian-flights-singles.txt Hawaiian-customers100.txt > my04.out

java MainAir Alaska-airports.txt Alaska-flights.txt Alaska-customers200.txt > my05.out
java MainAir Alaska-airports.txt Alaska-flights.txt Alaska-customers2000.txt > my06.out
java MainAir Alaska-airports.txt Alaska-flights.txt Alaska-customers20000.txt > my07.out

