#!/bin/bash

# Event Ticket Platform Frontend Setup Script
# This script sets up the frontend development environment

set -e

echo "🎉 Welcome to Event Ticket Platform Frontend Setup!"
echo "=================================================="

# Check if Node.js is installed
if ! command -v node &> /dev/null; then
    echo "❌ Node.js is not installed. Please install Node.js 18+ first."
    echo "   Visit: https://nodejs.org/"
    exit 1
fi

# Check Node.js version
NODE_VERSION=$(node -v | cut -d'v' -f2 | cut -d'.' -f1)
if [ "$NODE_VERSION" -lt 18 ]; then
    echo "❌ Node.js version 18+ is required. Current version: $(node -v)"
    echo "   Please upgrade Node.js to version 18 or higher."
    exit 1
fi

echo "✅ Node.js version: $(node -v)"

# Check if npm is installed
if ! command -v npm &> /dev/null; then
    echo "❌ npm is not installed. Please install npm first."
    exit 1
fi

echo "✅ npm version: $(npm -v)"

# Install dependencies
echo ""
echo "📦 Installing dependencies..."
npm install

# Create environment file
if [ ! -f .env.local ]; then
    echo ""
    echo "🔧 Creating environment configuration..."
    cp env.example .env.local
    echo "✅ Environment file created: .env.local"
    echo "   Please review and update the configuration as needed."
else
    echo "✅ Environment file already exists: .env.local"
fi

# Install Git hooks (only if in a git repository)
if [ -d "../.git" ] || [ -d ".git" ]; then
    echo ""
    echo "🔗 Installing Git hooks..."
    npm run setup-husky
else
    echo ""
    echo "⚠️  Skipping Git hooks installation (not in a git repository)"
fi

# Run type checking
echo ""
echo "🔍 Running TypeScript type checking..."
npm run type-check

# Run linting
echo ""
echo "🧹 Running code linting..."
npm run lint

# Run formatting
echo ""
echo "✨ Formatting code..."
npm run format

echo ""
echo "🎉 Setup completed successfully!"
echo "=================================================="
echo ""
echo "Next steps:"
echo "1. Review and update .env.local with your configuration"
echo "2. Start the development server: npm run dev"
echo "3. Open http://localhost:3000 in your browser"
echo ""
echo "Available commands:"
echo "  npm run dev          - Start development server"
echo "  npm run build        - Build for production"
echo "  npm run test         - Run tests"
echo "  npm run lint         - Run linting"
echo "  npm run format       - Format code"
echo ""
echo "Happy coding! 🚀" 