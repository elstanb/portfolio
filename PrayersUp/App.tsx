import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import HomePage from './HomePage';
import PrayerTime from './PrayerTime';
import PrayerList from './PrayerList';
import PrayerCall from './PrayerCall';

const Stack = createStackNavigator();

const App: React.FC = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName="Home"
        screenOptions={{
          headerShown: false,
        }}
      >
        <Stack.Screen name="Home" component={HomePage} />
        <Stack.Screen name="PrayerTime" component={PrayerTime} />
        <Stack.Screen name="PrayerList" component={PrayerList} />
        <Stack.Screen name="PrayerCall" component={PrayerCall} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App; 