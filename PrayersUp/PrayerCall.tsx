import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  SafeAreaView,
  StatusBar,
  TextInput,
  Alert,
  Linking,
  Dimensions,
} from 'react-native';
import { useNavigation } from '@react-navigation/native';

const { width, height } = Dimensions.get('window');

interface MeetingInfo {
  meetingId: string;
  password: string;
  hostName: string;
  startTime: string;
}

const PrayerCall: React.FC = () => {
  const navigation = useNavigation();
  const [isInMeeting, setIsInMeeting] = useState(false);
  const [meetingId, setMeetingId] = useState('');
  const [password, setPassword] = useState('');
  const [currentMeeting, setCurrentMeeting] = useState<MeetingInfo | null>(null);

  // Sample active meetings
  const activeMeetings: MeetingInfo[] = [
    {
      meetingId: '123456789',
      password: 'prayer123',
      hostName: 'Pastor John',
      startTime: '10:00 AM',
    },
    {
      meetingId: '987654321',
      password: 'faith456',
      hostName: 'Sister Mary',
      startTime: '2:00 PM',
    },
    {
      meetingId: '555666777',
      password: 'hope789',
      hostName: 'Brother David',
      startTime: '7:00 PM',
    },
  ];

  useEffect(() => {
    // Set the first meeting as current by default
    if (activeMeetings.length > 0) {
      setCurrentMeeting(activeMeetings[0]);
      setMeetingId(activeMeetings[0].meetingId);
      setPassword(activeMeetings[0].password);
    }
  }, []);

  const joinZoomMeeting = (meetingId: string, password: string) => {
    const zoomUrl = `zoom://zoom.us/join?confno=${meetingId}&pwd=${password}`;
    const webUrl = `https://zoom.us/j/${meetingId}?pwd=${password}`;
    
    // Try to open Zoom app first, fallback to web
    Linking.canOpenURL(zoomUrl).then(supported => {
      if (supported) {
        return Linking.openURL(zoomUrl);
      } else {
        return Linking.openURL(webUrl);
      }
    }).catch(err => {
      Alert.alert(
        'Zoom Not Found',
        'Please install the Zoom app or use the web version.',
        [
          { text: 'Cancel', style: 'cancel' },
          { text: 'Open Web', onPress: () => Linking.openURL(webUrl) },
        ]
      );
    });
  };

  const handleJoinMeeting = () => {
    if (!meetingId.trim()) {
      Alert.alert('Error', 'Please enter a meeting ID');
      return;
    }

    Alert.alert(
      'Join Prayer Call',
      `Join the prayer call with ${currentMeeting?.hostName || 'the host'}?`,
      [
        { text: 'Cancel', style: 'cancel' },
        {
          text: 'Join',
          onPress: () => {
            setIsInMeeting(true);
            joinZoomMeeting(meetingId, password);
          },
        },
      ]
    );
  };

  const handleLeaveMeeting = () => {
    Alert.alert(
      'Leave Meeting',
      'Are you sure you want to leave the prayer call?',
      [
        { text: 'Cancel', style: 'cancel' },
        {
          text: 'Leave',
          style: 'destructive',
          onPress: () => setIsInMeeting(false),
        },
      ]
    );
  };

  const selectMeeting = (meeting: MeetingInfo) => {
    setCurrentMeeting(meeting);
    setMeetingId(meeting.meetingId);
    setPassword(meeting.password);
  };

  const handleNavigation = (destination: string) => {
    console.log(`Navigating to ${destination}`);
    if (destination === 'Home') {
      navigation.navigate('Home' as never);
    }
  };

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="dark-content" backgroundColor="#f8f9fa" />
      
      {/* Header */}
      <View style={styles.header}>
        <Text style={styles.headerTitle}>Live Prayer Calls</Text>
        <Text style={styles.headerSubtitle}>
          Join live prayer sessions with your community
        </Text>
      </View>

      {/* Current Meeting Info */}
      {currentMeeting && (
        <View style={styles.currentMeetingContainer}>
          <View style={styles.meetingInfo}>
            <Text style={styles.meetingTitle}>Current Session</Text>
            <Text style={styles.hostName}>Host: {currentMeeting.hostName}</Text>
            <Text style={styles.meetingTime}>Time: {currentMeeting.startTime}</Text>
            <Text style={styles.meetingId}>ID: {currentMeeting.meetingId}</Text>
          </View>
          
          <View style={styles.meetingActions}>
            {!isInMeeting ? (
              <TouchableOpacity
                style={styles.joinButton}
                onPress={handleJoinMeeting}
              >
                <Text style={styles.joinButtonText}>Join Call</Text>
              </TouchableOpacity>
            ) : (
              <TouchableOpacity
                style={styles.leaveButton}
                onPress={handleLeaveMeeting}
              >
                <Text style={styles.leaveButtonText}>Leave Call</Text>
              </TouchableOpacity>
            )}
          </View>
        </View>
      )}

      {/* Meeting ID Input */}
      <View style={styles.inputContainer}>
        <Text style={styles.inputLabel}>Or join with meeting ID:</Text>
        <TextInput
          style={styles.meetingIdInput}
          placeholder="Enter Meeting ID"
          value={meetingId}
          onChangeText={setMeetingId}
          keyboardType="numeric"
        />
        <TextInput
          style={styles.passwordInput}
          placeholder="Enter Password (if required)"
          value={password}
          onChangeText={setPassword}
          secureTextEntry
        />
        <TouchableOpacity
          style={styles.customJoinButton}
          onPress={handleJoinMeeting}
        >
          <Text style={styles.customJoinButtonText}>Join Meeting</Text>
        </TouchableOpacity>
      </View>

      {/* Active Meetings List */}
      <View style={styles.meetingsSection}>
        <Text style={styles.sectionTitle}>Active Prayer Sessions</Text>
        <View style={styles.meetingsList}>
          {activeMeetings.map((meeting, index) => (
            <TouchableOpacity
              key={index}
              style={[
                styles.meetingCard,
                currentMeeting?.meetingId === meeting.meetingId && styles.selectedMeeting
              ]}
              onPress={() => selectMeeting(meeting)}
            >
              <View style={styles.meetingCardContent}>
                <Text style={styles.meetingCardHost}>{meeting.hostName}</Text>
                <Text style={styles.meetingCardTime}>{meeting.startTime}</Text>
                <Text style={styles.meetingCardId}>ID: {meeting.meetingId}</Text>
              </View>
              <View style={styles.liveIndicator}>
                <View style={styles.liveDot} />
                <Text style={styles.liveText}>LIVE</Text>
              </View>
            </TouchableOpacity>
          ))}
        </View>
      </View>

      {/* Navigation Buttons */}
      <View style={styles.navigationContainer}>
        <TouchableOpacity
          style={[styles.navButton, styles.centerButton]}
          onPress={() => handleNavigation('Home')}
        >
          <Text style={styles.navButtonText}>Home</Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#D9BEF4',
  },
  header: {
    paddingTop: 20,
    paddingHorizontal: 20,
    paddingBottom: 15,
    backgroundColor: '#FFFFFF',
    borderBottomWidth: 1,
    borderBottomColor: '#e9ecef',
  },
  headerTitle: {
    fontSize: 26,
    fontWeight: 'bold',
    color: '#1a1a1a',
    marginBottom: 5,
    fontFamily: 'sans-serif',
  },
  headerSubtitle: {
    fontSize: 16,
    color: '#6c757d',
    fontFamily: 'sans-serif',
  },
  currentMeetingContainer: {
    backgroundColor: '#FFFFFF',
    margin: 20,
    borderRadius: 15,
    padding: 20,
    elevation: 3,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
  },
  meetingInfo: {
    marginBottom: 15,
  },
  meetingTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#1a1a1a',
    marginBottom: 8,
    fontFamily: 'sans-serif',
  },
  hostName: {
    fontSize: 16,
    color: '#495057',
    marginBottom: 4,
    fontFamily: 'sans-serif',
  },
  meetingTime: {
    fontSize: 14,
    color: '#6c757d',
    marginBottom: 4,
    fontFamily: 'sans-serif',
  },
  meetingId: {
    fontSize: 14,
    color: '#6c757d',
    fontFamily: 'sans-serif',
  },
  meetingActions: {
    alignItems: 'center',
  },
  joinButton: {
    backgroundColor: '#FFD700',
    paddingHorizontal: 28,
    paddingVertical: 10,
    borderRadius: 22,
    elevation: 2,
  },
  joinButtonText: {
    color: '#000000',
    fontSize: 14,
    fontWeight: '600',
    fontFamily: 'sans-serif',
  },
  leaveButton: {
    backgroundColor: '#dc3545',
    paddingHorizontal: 30,
    paddingVertical: 12,
    borderRadius: 25,
    elevation: 2,
  },
  leaveButtonText: {
    color: '#FFFFFF',
    fontSize: 16,
    fontWeight: '600',
    fontFamily: 'sans-serif',
  },
  inputContainer: {
    backgroundColor: '#FFFFFF',
    margin: 20,
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
  inputLabel: {
    fontSize: 16,
    fontWeight: '600',
    color: '#1a1a1a',
    marginBottom: 10,
    fontFamily: 'sans-serif',
  },
  meetingIdInput: {
    backgroundColor: '#FFFFFF',
    borderRadius: 8,
    padding: 12,
    fontSize: 16,
    marginBottom: 10,
    borderWidth: 1,
    borderColor: '#FFD700',
    fontFamily: 'sans-serif',
  },
  passwordInput: {
    backgroundColor: '#FFFFFF',
    borderRadius: 8,
    padding: 12,
    fontSize: 16,
    marginBottom: 15,
    borderWidth: 1,
    borderColor: '#FFD700',
    fontFamily: 'sans-serif',
  },
  customJoinButton: {
    backgroundColor: '#FFD700',
    paddingVertical: 10,
    borderRadius: 22,
    alignItems: 'center',
  },
  customJoinButtonText: {
    color: '#000000',
    fontSize: 14,
    fontWeight: '600',
    fontFamily: 'sans-serif',
  },
  meetingsSection: {
    flex: 1,
    paddingHorizontal: 20,
  },
  sectionTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#1a1a1a',
    marginBottom: 15,
    fontFamily: 'sans-serif',
  },
  meetingsList: {
    flex: 1,
  },
  meetingCard: {
    backgroundColor: '#FFFFFF',
    borderRadius: 10,
    padding: 15,
    marginBottom: 10,
    flexDirection: 'row',
    alignItems: 'center',
    elevation: 1,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowOpacity: 0.22,
    shadowRadius: 2.22,
  },
  selectedMeeting: {
    borderWidth: 2,
    borderColor: '#FFD700',
  },
  meetingCardContent: {
    flex: 1,
  },
  meetingCardHost: {
    fontSize: 16,
    fontWeight: '600',
    color: '#1a1a1a',
    marginBottom: 4,
    fontFamily: 'sans-serif',
  },
  meetingCardTime: {
    fontSize: 14,
    color: '#6c757d',
    marginBottom: 2,
    fontFamily: 'sans-serif',
  },
  meetingCardId: {
    fontSize: 12,
    color: '#adb5bd',
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
    fontSize: 12,
    fontWeight: 'bold',
    fontFamily: 'sans-serif',
  },
  navigationContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingHorizontal: 20,
    paddingBottom: 30,
    paddingTop: 20,
  },
  navButton: {
    backgroundColor: '#FFD700',
    paddingHorizontal: 23,
    paddingVertical: 13,
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
  leftButton: {
    flex: 0.48,
  },
  centerButton: {
    flex: 0.48,
  },
  navButtonText: {
    color: '#000000',
    fontSize: 14,
    fontWeight: '600',
    textAlign: 'center',
    fontFamily: 'sans-serif',
  },
});

export default PrayerCall;
