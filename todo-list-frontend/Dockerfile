# ── Stage 1: Build Angular ──────────────────────────────────
FROM node:20-alpine AS builder

WORKDIR /app

# Copy lockfiles first for optimal caching
COPY package*.json ./
RUN npm ci

# Copy remaining source code
COPY . .
RUN npm run build -- --configuration production

# ── Stage 2: Serve with Nginx ───────────────────────────────
FROM nginx:alpine

# Remove default nginx static assets
RUN rm -rf /usr/share/nginx/html/*

# Copy Angular build output (matches the exact dist folder structure)
COPY --from=builder /app/dist/todo-list-frontend/browser /usr/share/nginx/html

# Apply our custom Nginx routing fallback configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
