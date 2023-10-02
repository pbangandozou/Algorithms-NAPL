public static String rotateString(String string, int n) {
  // Check if the string is empty.
  if (string.isEmpty()) {
    return "";
  }

  // Check if the number of characters to rotate by is greater than the length of the string.
  if (n >= string.length()) {
    n = n % string.length();
  }

  // Split the string into two parts: the part to rotate and the part to keep.
  String rotatedPart = string.substring(0, n);
  String remainingPart = string.substring(n);

  // Return the rotated string.
  return remainingPart + rotatedPart;
}
