### STAGE 1: Build ###
FROM node:13.12.0-alpine as build
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build


CMD ["npm", "start"]