FROM nginx:alpine

WORKDIR /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY ./dist ./

COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

ENV VUE_APP_API_URL http://sh-backend:8080/api

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

