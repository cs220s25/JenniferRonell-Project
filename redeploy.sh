cd /home/ec2-user/JenniferRonell-Project
sudo systemctl stop discord_bot
sudo git pull origin main
mvn clean package
sudo systemctl restart discord_bot
