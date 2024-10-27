# entrypoint.sh
#!/bin/sh

if [ -n "$VUE_APP_API_URL" ]; then
    sed -i "s|__VUE_APP_API_URL__|$VUE_APP_API_URL|g" /usr/share/nginx/html/js/app.*.js
fi

exec nginx -g "daemon off;"
