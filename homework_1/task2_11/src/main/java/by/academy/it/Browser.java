package by.academy.it;

public enum Browser {
    OPERA("OPR"),
    INTERNET_EXPLORER("Trident"),
    FIREFOX("Firefox"),
    EDGE("Edge"),
    CHROME("Chrome"),
    SAFARI("Safari"),
    DEFAULT("Unknown");

    private final String browserName;

    Browser(String browserName) {
        this.browserName = browserName;
    }

    public static Browser getBrowser(String userAgent){
        for (Browser browser : values()) {
            if(userAgent.toLowerCase().contains(browser.browserName.toLowerCase())){
                return browser;
            }
        }
        return DEFAULT;
    }

}
