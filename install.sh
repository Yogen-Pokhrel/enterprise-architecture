#!/bin/bash
echo "🚀 Starting the enterprise application project installation..."

CURRENT_DIR=$(pwd)

# Set default paths for kind config file and Kubernetes config files
KIND_CONFIG_FILE="${1:-$CURRENT_DIR/deployment/cluster-config/demo-kindcluster.yml}"
CONFIG_FILES_PATH="${2:-$CURRENT_DIR/deployment/project/}"
METRIC_CONFIG_FILE_PATH="${3:-$CURRENT_DIR/deployment/metric-api/metrics-server.yaml}"
CLUSTER_ROLE_BINDING_ADMIN_PATH="${4:-$CURRENT_DIR/deployment/dashboard/dashboard-admin.yaml}"

# Check if the cluster already exists
if kind get clusters | grep -q "ea-project-kube"; then
  echo "Kind cluster 'ea-project-kube' already exists. Skipping creation."
else
  # Create a kind cluster with the specified config file
  echo "Creating kind cluster using config file $KIND_CONFIG_FILE..."
  kind create cluster --name ea-project-kube --config "$KIND_CONFIG_FILE"
  
  # Check if the cluster creation was successful
  if [ $? -ne 0 ]; then
    echo "Failed to create kind cluster."
    exit 1
  fi
fi

# Check if the cluster creation was successful
if [ $? -ne 0 ]; then
  echo "Failed to create kind cluster."
  exit 1
fi

# Apply Kubernetes configuration files
echo "Applying Kubernetes configuration files from $CONFIG_FILES_PATH..."

# Check if the directory exists
if [ ! -d "$CONFIG_FILES_PATH" ]; then
  echo "Directory $CONFIG_FILES_PATH does not exist."
  exit 1
fi

# Get the full paths of all .yaml files in the directory
FILES=$(find "$CONFIG_FILES_PATH" -type f -name "*.yml")

# Apply each configuration file
for file in $FILES; do
  echo "Applying $file..."
  kubectl apply -f "$file"
done

echo "✅ Kubernetes cluster setup and configuration files applied successfully."

echo "🛠️ Fixing metrics server api error"
# configure metrics api
kubectl apply -f $METRIC_CONFIG_FILE_PATH 
kubectl get --raw /api/v1/nodes/ea-project-kube-control-plane/proxy/metrics/resource
echo "😃 Successfully fixed metrics server api"

# Configure the dashboard
helm repo add kubernetes-dashboard https://kubernetes.github.io/dashboard/
helm upgrade --install kubernetes-dashboard kubernetes-dashboard/kubernetes-dashboard --create-namespace --namespace kubernetes-dashboard

# apply the cluster role binding for the admin
kubectl apply -f $CLUSTER_ROLE_BINDING_ADMIN_PATH
echo "🏃 Application ready to use."
