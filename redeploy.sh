cd /home/ec2-user/JenniferRonell-Project
sudo systemctl stop discord_bot.service
sudo pkill -f 'target/dbot-1.0-SNAPSHOT.jar' || true
sudo rm -rf target/
sudo git pull origin main
mvn clean package
sudo systemctl start discord_bot.service
