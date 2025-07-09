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
import { useNavigation } from '@react-navigation/native';

interface FeedItem {
  id: string;
  title: string;
  content: string;
  timestamp: string;
  type: 'global' | 'local';
  location?: string;
}

const HomePage: React.FC = () => {
  const navigation = useNavigation();
  const [feedItems, setFeedItems] = useState<FeedItem[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [activeTab, setActiveTab] = useState<'global' | 'local'>('global');

  // Simulate live feed data
  useEffect(() => {
    const generateFeedData = () => {
      const mockData: FeedItem[] = [
        {
          id: '1',
          title: 'Prayer Request',
          content: 'Please pray for healing and strength during this difficult time.',
          timestamp: '2 minutes ago',
          type: 'local',
          location: 'New York, NY',
        },
        {
          id: '2',
          title: 'Praise Report',
          content: 'Thank you for your prayers! The surgery was successful.',
          timestamp: '15 minutes ago',
          type: 'local',
          location: 'Los Angeles, CA',
        },
        {
          id: '3',
          title: 'Community Update',
          content: 'Join us for our weekly prayer meeting this Sunday at 10 AM.',
          timestamp: '1 hour ago',
          type: 'local',
          location: 'Chicago, IL',
        },
        {
          id: '4',
          title: 'Prayer Request',
          content: 'Praying for peace and comfort for families affected by recent events.',
          timestamp: '2 hours ago',
          type: 'global',
        },
        {
          id: '5',
          title: 'Praise Report',
          content: 'God answered our prayers! The job opportunity came through.',
          timestamp: '3 hours ago',
          type: 'local',
          location: 'Miami, FL',
        },
        {
          id: '6',
          title: 'Global Prayer Request',
          content: 'Please pray for peace in the Middle East and for all those affected by conflict.',
          timestamp: '30 minutes ago',
          type: 'global',
        },
        {
          id: '7',
          title: 'Natural Disaster Prayer',
          content: 'Praying for those affected by recent earthquakes and natural disasters worldwide.',
          timestamp: '1 hour ago',
          type: 'global',
        },
        {
          id: '8',
          title: 'Local Church Event',
          content: 'Youth group meeting this Friday at 7 PM. All are welcome!',
          timestamp: '4 hours ago',
          type: 'local',
          location: 'Dallas, TX',
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
        type: Math.random() > 0.5 ? 'global' : 'local',
        location: Math.random() > 0.5 ? 'Local Community' : undefined,
      };
      setFeedItems(prev => [newItem, ...prev.slice(0, 7)]);
    }, 30000);

    return () => clearInterval(interval);
  }, []);

  const handleButtonPress = (buttonName: string) => {
    console.log(`${buttonName} button pressed`);
    if (buttonName === 'Bottom Center') {
      navigation.navigate('PrayerTime' as never);
    } else if (buttonName === 'Bottom Left') {
      navigation.navigate('PrayerList' as never);
    } else if (buttonName === 'Bottom Right') {
      navigation.navigate('PrayerCall' as never);
    }
    // Add your navigation or action logic here for other buttons
  };

  const renderFeedItem = (item: FeedItem) => (
    <View key={item.id} style={styles.feedItem}>
      <View style={styles.feedHeader}>
        <View style={styles.feedTitleContainer}>
          <Text style={styles.feedTitle}>{item.title}</Text>
          {item.type === 'local' && item.location && (
            <Text style={styles.feedLocation}>{item.location}</Text>
          )}
        </View>
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

        {/* Tab Navigation */}
        <View style={styles.tabContainer}>
          <TouchableOpacity
            style={[styles.tabButton, activeTab === 'global' && styles.activeTabButton]}
            onPress={() => setActiveTab('global')}
          >
            <Text style={[styles.tabText, activeTab === 'global' && styles.activeTabText]}>
              Global Prayers
            </Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={[styles.tabButton, activeTab === 'local' && styles.activeTabButton]}
            onPress={() => setActiveTab('local')}
          >
            <Text style={[styles.tabText, activeTab === 'local' && styles.activeTabText]}>
              Local Prayers
            </Text>
          </TouchableOpacity>
        </View>
        
        <ScrollView style={styles.feedScrollView} showsVerticalScrollIndicator={false}>
          {isLoading ? (
            <View style={styles.loadingContainer}>
              <Text style={styles.loadingText}>Loading feed...</Text>
            </View>
          ) : (
            feedItems
              .filter(item => item.type === activeTab)
              .map(renderFeedItem)
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
    backgroundColor: '#D9BEF4',
  },
  button: {
    position: 'absolute',
    backgroundColor: '#FFD700',
    paddingHorizontal: 8,
    paddingVertical: 10,
    borderRadius: 22,
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowOpacity: 0.2,
    shadowRadius: 2.5,
  },
  buttonText: {
    color: '#000000',
    fontSize: 14,
    fontWeight: '600',
    textAlign: 'center',
    fontFamily: 'sans-serif',
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
    borderRadius: 18,
    padding: 22,
    elevation: 3,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.15,
    shadowRadius: 3.5,
  },
  feedHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 20,
  },
  feedSectionTitle: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#1a1a1a',
    fontFamily: 'sans-serif',
  },
  liveIndicator: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#FFD700',
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
    color: '#000000',
    fontSize: 10,
    fontWeight: 'bold',
    fontFamily: 'sans-serif',
  },
  feedScrollView: {
    flex: 1,
  },
  feedItem: {
    backgroundColor: '#FFFFFF',
    padding: 16,
    borderRadius: 12,
    marginBottom: 16,
    borderLeftWidth: 3,
    borderLeftColor: '#FFD700',
    elevation: 1,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowOpacity: 0.1,
    shadowRadius: 2,
  },
  feedTitle: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#1a1a1a',
    marginBottom: 5,
    fontFamily: 'sans-serif',
  },
  feedTitleContainer: {
    flex: 1,
  },
  feedLocation: {
    fontSize: 12,
    color: '#FFD700',
    fontWeight: '600',
    fontFamily: 'sans-serif',
  },
  feedTimestamp: {
    fontSize: 12,
    color: '#6c757d',
    fontFamily: 'sans-serif',
  },
  feedContent: {
    fontSize: 12,
    color: '#495057',
    lineHeight: 18,
    marginTop: 8,
    fontFamily: 'sans-serif',
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  loadingText: {
    fontSize: 16,
    color: '#6c757d',
    fontFamily: 'sans-serif',
  },
  tabContainer: {
    flexDirection: 'row',
    marginBottom: 16,
    backgroundColor: '#f8f9fa',
    borderRadius: 12,
    padding: 5,
    marginHorizontal: 5,
    elevation: 1,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowOpacity: 0.1,
    shadowRadius: 2,
  },
  tabButton: {
    flex: 1,
    paddingVertical: 11,
    paddingHorizontal: 16,
    borderRadius: 10,
    alignItems: 'center',
  },
  activeTabButton: {
    backgroundColor: '#FFD700',
  },
  tabText: {
    fontSize: 12,
    fontWeight: '600',
    color: '#6c757d',
    fontFamily: 'sans-serif',
  },
  activeTabText: {
    color: '#000000',
  },
});

export default HomePage;
