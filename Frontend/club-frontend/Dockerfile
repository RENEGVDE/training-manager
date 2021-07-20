# FROM node:alpine
# WORKDIR '/club-frontend'
# ENV PATH="./node_modules/.bin:$PATH"
# COPY . .
# RUN npm run build
# CMD ["npm", "start"]

FROM node:alpine
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
COPY package-lock.json ./
RUN npm install --silent
RUN npm install react-scripts@3.4.1 -g --silent
COPY . ./
CMD ["npm", "start"]