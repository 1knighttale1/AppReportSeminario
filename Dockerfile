FROM node
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY package.json .
RUN npm install --quiet
RUN npm install nodemon -g --quiet
COPY . .
EXPOSE 8000
CMD nodemon -L --watch . app.js
