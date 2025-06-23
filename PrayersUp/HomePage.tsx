import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ScrollView,
  SafeAreaView,
  StatusBar,
} from 'react-native';

interface FeedItem {
  id: string;
  title: string;
  content: string;
  timestamp: string;
}

const HomePage: React.FC = () => {
  const [feedItems, setFeedItems] = useState<FeedItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  // Simulate live feed data
  useEffect(() => {
    const generateFeedData = () => {
      const mockData: FeedItem[] = [
        {
          id: '1',
          title: 'Prayer Request',
          content: 'Please pray for healing and strength during this difficult time.',
          timestamp: '2 minutes ago',
        },
        {
          id: '2',
          title: 'Praise Report',
          content: 'Thank you for your prayers! The surgery was successful.',
          timestamp: '15 minutes ago',
        },
        {
          id: '3',
          title: 'Community Update',
          content: 'Join us for our weekly prayer meeting this Sunday at 10 AM.',
          timestamp: '1 hour ago',
        },
        {
          id: '4',
          title: 'Prayer Request',
          content: 'Praying for peace and comfort for families affected by recent events.',
          timestamp: '2 hours ago',
        },
        {
          id: '5',
          title: 'Praise Report',
          content: 'God answered our prayers! The job opportunity came through.',
          timestamp: '3 hours ago',
        },
      ];
      setFeedItems(mockData);
      setIsLoading(false);
    };

    generateFeedData();
    
    // Simulate real-time updates every 30 seconds
    const interval = setInterval(() => {
      const newItem: FeedItem = {
        id: Date.now().toString(),
        title: 'New Prayer Request',
        content: 'Please keep us in your thoughts and prayers.',
        timestamp: 'Just now',
      };
      setFeedItems(prev => [newItem, ...prev.slice(0, 4)]);
    }, 30000);

    return () => clearInterval(interval);
  }, []);

  const handleButtonPress = (buttonName: string) => {
    console.log(`${buttonName} button pressed`);
    // Add your navigation or action logic here
  };

  const renderFeedItem = (item: FeedItem) => (
    <View key={item.id} style={styles.feedItem}>
      <View style={styles.feedHeader}>
        <Text style={styles.feedTitle}>{item.title}</Text>
        <Text style={styles.feedTimestamp}>{item.timestamp}</Text>
      </View>
      <Text style={styles.feedContent}>{item.content}</Text>
    </View>
  );

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="dark-content" backgroundColor="#f8f9fa" />
      
      {/* Top Left Button */}
      <TouchableOpacity
        style={[styles.button, styles.topLeftButton]}
        onPress={() => handleButtonPress('Top Left')}
      >
        <Text style={styles.buttonText}>Upped Prayer</Text>
      </TouchableOpacity>

      {/* Top Right Button */}
      <TouchableOpacity
        style={[styles.button, styles.topRightButton]}
        onPress={() => handleButtonPress('Top Right')}
      >
        <Text style={styles.buttonText}>Direct Messages</Text>
      </TouchableOpacity>

      {/* Live Feed Section */}
      <View style={styles.feedContainer}>
        <View style={styles.feedHeader}>
          <Text style={styles.feedSectionTitle}>Live Prayer Feed</Text>
          <View style={styles.liveIndicator}>
            <View style={styles.liveDot} />
            <Text style={styles.liveText}>LIVE</Text>
          </View>
        </View>
        
        <ScrollView style={styles.feedScrollView} showsVerticalScrollIndicator={false}>
          {isLoading ? (
            <View style={styles.loadingContainer}>
              <Text style={styles.loadingText}>Loading feed...</Text>
            </View>
          ) : (
            feedItems.map(renderFeedItem)
          )}
        </ScrollView>
      </View>

      {/* Bottom Left Button */}
      <TouchableOpacity
        style={[styles.button, styles.bottomLeftButton]}
        onPress={() => handleButtonPress('Bottom Left')}
      >
        <Text style={styles.buttonText}>Prayer List</Text>
      </TouchableOpacity>

      {/* Bottom Center Button */}
      <TouchableOpacity
        style={[styles.button, styles.bottomCenterButton]}
        onPress={() => handleButtonPress('Bottom Center')}
      >
        <Text style={styles.buttonText}>Prayer Time</Text>
      </TouchableOpacity>

      {/* Bottom Right Button */}
      <TouchableOpacity
        style={[styles.button, styles.bottomRightButton]}
        onPress={() => handleButtonPress('Bottom Right')}
      >
        <Text style={styles.buttonText}>Prayer Calls</Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f8f9fa',
  },
  button: {
    position: 'absolute',
    backgroundColor: '#007AFF',
    paddingHorizontal: 20,
    paddingVertical: 12,
    borderRadius: 25,
    elevation: 3,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
  },
  buttonText: {
    color: '#FFFFFF',
    fontSize: 16,
    fontWeight: '600',
    textAlign: 'center',
  },
  topLeftButton: {
    top: 60,
    left: 20,
  },
  topRightButton: {
    top: 60,
    right: 20,
  },
  bottomLeftButton: {
    bottom: 30,
    left: 20,
  },
  bottomCenterButton: {
    bottom: 30,
    left: '50%',
    transform: [{ translateX: -50 }],
  },
  bottomRightButton: {
    bottom: 30,
    right: 20,
  },
  feedContainer: {
    flex: 1,
    marginTop: 120,
    marginBottom: 100,
    marginHorizontal: 20,
    backgroundColor: '#FFFFFF',
    borderRadius: 15,
    padding: 20,
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowOpacity: 0.22,
    shadowRadius: 2.22,
  },
  feedHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 20,
  },
  feedSectionTitle: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#1a1a1a',
  },
  liveIndicator: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#FF3B30',
    paddingHorizontal: 8,
    paddingVertical: 4,
    borderRadius: 12,
  },
  liveDot: {
    width: 8,
    height: 8,
    borderRadius: 4,
    backgroundColor: '#FFFFFF',
    marginRight: 4,
  },
  liveText: {
    color: '#FFFFFF',
    fontSize: 12,
    fontWeight: 'bold',
  },
  feedScrollView: {
    flex: 1,
  },
  feedItem: {
    backgroundColor: '#f8f9fa',
    padding: 15,
    borderRadius: 10,
    marginBottom: 15,
    borderLeftWidth: 4,
    borderLeftColor: '#007AFF',
  },
  feedTitle: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#1a1a1a',
    marginBottom: 5,
  },
  feedTimestamp: {
    fontSize: 12,
    color: '#6c757d',
  },
  feedContent: {
    fontSize: 14,
    color: '#495057',
    lineHeight: 20,
    marginTop: 8,
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  loadingText: {
    fontSize: 16,
    color: '#6c757d',
  },
});

export default HomePage;
