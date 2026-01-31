package originalnamebot.bot;

/**
 * Lines that the bot says
 */
public enum BotLines {
    GREETING {
        @Override
        public String toString() {
            return "Hello from original name bot! As you can see, this is a very original name. What can I do for you?";
        }
    },
    GOODBYE {
        @Override
        public String toString() {
            return "Whew, finally. Bye.";
        }
    },
    UNKNOWN_COMMAND {
        @Override
        public String toString() {
            return "Invalid command";
        }
    },
    BAD_COMMAND_FORMAT {
        @Override
        public String toString() {
            return "Invalid format for task type!";
        }
    },
    NO_SUCH_TASK_AT_INDEX {
        @Override
        public String toString() {
            return "You don't have that many tasks!";
        }
    },
    TASK_DONE {
        @Override
        public String toString() {
            return "Congratulations, you did something you were supposed to do!";
        }
    },
    TASK_DELETED {
        @Override
        public String toString() {
            return "Good, please keep doing this so I don't have to remember so many things. Removed:";
        }
    },
    TASK_UNMARKED {
        @Override
        public String toString() {
            return "Why?";
        }
    }

}
