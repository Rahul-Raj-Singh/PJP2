# Select base node image
FROM node:12

# Setup folder as working directory within container
WORKDIR /app

COPY package.json /app

# Install all dependencies from package.json
RUN npm install

COPY . /app

CMD ["node", "app"]