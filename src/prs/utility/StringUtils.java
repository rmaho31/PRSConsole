package prs.utility;
public class StringUtils
{
    public static String padWithSpaces(String s, int length)
    {
        if (s.length() < length)
        {
            StringBuilder sb = new StringBuilder(s);
            while(sb.length() < length)
            {
                sb.append(" ");
            }
            return sb.toString();
        }
        else
        {
            return s.substring(0, length);
        }
    }
    
    public static String padWithEquals(String s, int length)
    {
        if (s.length() < length)
        {
            StringBuilder sb = new StringBuilder();
            while(sb.length() < (length-s.length())/2)
            {
                sb.append("=");
            }
            sb.append(s);
            while(sb.length() < (length))
            {
                sb.append("=");
            }
            return sb.toString();
        }
        else
        {
            return s.substring(0, length);
        }
    }
    
    public static String padWithUnderscores(String s, int length)
    {
        if (s.length() < length)
        {
            StringBuilder sb = new StringBuilder(s);
            while(sb.length() < length)
            {
                sb.append("_");
            }
            return sb.toString();
        }
        else
        {
            return s.substring(0, length);
        }
    }
}