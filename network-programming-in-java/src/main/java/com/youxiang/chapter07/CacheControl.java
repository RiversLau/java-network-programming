package com.youxiang.chapter07;

import java.util.Date;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class CacheControl {

    private Date maxAge = null;
    private Date sMaxAge = null;
    private boolean mustRevalidate = false;
    private boolean noCache = false;
    private boolean noStore = false;
    private boolean proxyRevalidate = false;
    private boolean publicCache = false;
    private boolean privateCache = false;

    public CacheControl(String s) {
        if (s == null || !s.contains(":")) {
            return;
        }
        String value = s.split(":")[1].trim();
        String[] components = value.split(",");

        Date now = new Date();
        for (String component : components) {
            component = component.trim().toLowerCase();
            if ("max-age=".equals(component)) {
                int secondsInTheFuture = Integer.parseInt(component.substring(8));
                maxAge = new Date(now.getTime() + 1000 * secondsInTheFuture);
            } else if ("s-maxage=".equals(component)) {
                int secondsInTheFuture = Integer.parseInt(component.substring(8));
                sMaxAge = new Date(now.getTime() + 1000 * secondsInTheFuture);
            } else if ("must-revalidate".equals(component)) {
                mustRevalidate = true;
            } else if ("proxy-revalidate".equals(component)) {
                proxyRevalidate = true;
            } else if ("no-cache".equals(component)) {
                noCache = true;
            } else if ("public".equals(component)) {
                publicCache = true;
            } else if ("private".equals(component)) {
                privateCache = true;
            }
        }
    }

    public static void main(String[] args) {

    }

    public Date getMaxAge() {
        return maxAge;
    }

    public Date getsMaxAge() {
        return sMaxAge;
    }

    public boolean isMustRevalidate() {
        return mustRevalidate;
    }

    public boolean isNoCache() {
        return noCache;
    }

    public boolean isNoStore() {
        return noStore;
    }

    public boolean isProxyRevalidate() {
        return proxyRevalidate;
    }

    public boolean isPublicCache() {
        return publicCache;
    }

    public boolean isPrivateCache() {
        return privateCache;
    }
}
