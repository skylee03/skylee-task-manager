package skylee.ui;

/**
 * Defines string literals for the output messages.
 */
public class Message {
    public static final String LINE = "____________________________________________________________\n";
    public static final String PREFIX_MESSAGE = " ";
    public static final String PREFIX_TASK = "  ";
    public static final String PREFIX_EXCEPTION = "☹ OOPS!!! ";
    public static final String[] MESSAGE_HELLO = {"Hello! I'm Skylee!", "What can I do for you?"};
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_UNKNOWN_COMMAND = "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_ID_FORMAT = "Task ID must be an integer.";
    public static final String MESSAGE_ID_OUT_OF_RANGE = "Task ID is out of range.";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_FIND = "Here are the matching tasks in your list:";
    public static final String MESSAGE_DATE = "Here are the tasks occurring on the given date:";
    public static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_MARK = "Nice! I've marked this task as done:";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task:";
    public static final String MESSAGE_ADD = "Got it. I've added this task:";
    public static final String MESSAGE_COUNT = "Now you have %d task%s in the list.";
    public static final String MESSAGE_BY_MISSING = "The \"by\" field is missing.";
    public static final String MESSAGE_DEADLINE_EMPTY = "The \"description\" field of a deadline cannot be empty.";
    public static final String MESSAGE_BY_EMPTY = "The \"by\" field of a deadline cannot be empty.";
    public static final String MESSAGE_TODO_EMPTY = "The \"description\" field of a todo cannot be empty.";
    public static final String MESSAGE_FROM_MISSING = "The \"from\" field is missing.";
    public static final String MESSAGE_TO_MISSING = "The \"to\" field is missing.";
    public static final String MESSAGE_EVENT_EMPTY = "The \"description\" field of an event cannot be empty.";
    public static final String MESSAGE_FROM_EMPTY = "The \"from\" field of an event cannot be empty.";
    public static final String MESSAGE_TO_EMPTY = "The \"to\" field of an event cannot be empty.";
    public static final String MESSAGE_FILE_FORMAT_ERROR = "The file is not in the expected format.";
    public static final String MESSAGE_TIME_FORMAT_ERROR = "The time is not in the expected format.";
    public static final String MESSAGE_IO_EXCEPTION = "An I/O exception occurred.";
}
