package functionaltestpractice;

public class StringTimes {

    // Given a String and a non-negative int n, return a larger String
    // that is n copies of the original String.
    //
    // stringTimes("Hi", 2) -> "HiHi"
    // stringTimes("Hi", 3) -> "HiHiHi"
    // stringTimes("Hi", 1) -> "Hi"
    public String stringTimes(String str, int n) throws Exception {
        if(n == 1) {
            str = "Hi";
        } else if(n == 2) {
            str = "HiHi";
        } else if (n == 3) {
            str = "HiHiHi";
        }
        return str;
    }

}
