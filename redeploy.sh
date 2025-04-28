cd /home/ec2-user/JenniferRonell-Project
sudo git pull origin main
mvn clean package
sudo systemctl restart discord_bot
