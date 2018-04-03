# Login logic

If the user requests a page, lets say `page.html` and they are logged in,
then they go straight to the page.

If the user isn't logged in then the following happens

1. You store the requested page URL in the session, under key `redirectURL`
2. You redirect to the `login.html` page
3. The user logins in, just by typing their name (can add passwords later)
4. The user then, presses the login button and gets redirected to the `redirectURL` page