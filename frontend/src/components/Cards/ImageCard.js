import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import { Card, ListItem, Button, Icon } from 'react-native-elements'

export default function ImageCard() {
  return (
    <Card
      title='SOME TITLE'
      image={require('../../images/toronto.jpg')}>
      <Text style={{marginBottom: 10}}>
      LMAO THIS CARD HELLA UGLY TBH
      </Text>
      <Button
      icon={<Icon name='code' color='#ffffff' />}
      buttonStyle={{borderRadius: 0, marginLeft: 0, marginRight: 0, marginBottom: 0}}
      title='VIEW EVENT' />
    </Card>
  );
}
